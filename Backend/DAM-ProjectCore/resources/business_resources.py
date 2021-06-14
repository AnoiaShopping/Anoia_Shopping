#!/usr/bin/python
# -*- coding: utf-8 -*-

import logging

import falcon
from falcon.media.validators import jsonschema
from sqlalchemy.exc import IntegrityError
from sqlalchemy.orm.exc import NoResultFound

import messages
from resources import utils
from db.models import User, Business
from hooks import requires_auth
from resources.base_resources import DAMCoreResource

mylogger = logging.getLogger(__name__)

@falcon.before(requires_auth)
class ResourceCreateBusiness(DAMCoreResource):

    def on_post(self, req, resp, *args, **kwargs):
        super(ResourceCreateBusiness, self).on_post(req, resp, *args, **kwargs)

        aux_business = Business()

        try:
            aux_business.name = req.media["name"]
            aux_business.type = req.media["type"]
            aux_business.definition = req.media["definition"]
            aux_business.web = req.media["web"]
            aux_business.instagram = req.media["instagram"]
            aux_business.facebook = req.media["facebook"]
            aux_business.twitter = req.media["twitter"]
            aux_business.owner_id = req.context["auth_user"].id

            self.db_session.add(aux_business)

            try:
                self.db_session.commit()
            except IntegrityError:
                raise falcon.HTTPBadRequest(description=messages.business_exists)

        except KeyError:
            raise falcon.HTTPBadRequest(description=messages.parameters_invalid)

        resp.status = falcon.HTTP_200


@falcon.before(requires_auth)
class ResourceGetBusiness(DAMCoreResource):

    def on_get(self, req, resp, *args, **kwargs):
        super(ResourceGetBusiness, self).on_get(req, resp, *args, **kwargs)

        current_user = req.context["auth_user"]
        cursor = self.db_session.query(Business).filter(Business.owner_id != current_user.id)
        cursor = cursor.order_by(Business.name.asc())
        business = list()

        for b in cursor.all():
            business.append(b.json_model)

        resp.media = business

        resp.status = falcon.HTTP_200


@falcon.before(requires_auth)
class ResourceGetOwnBusiness(DAMCoreResource):

    def on_get(self, req, resp, *args, **kwargs):
        super(ResourceGetOwnBusiness, self).on_get(req, resp, *args, **kwargs)

        current_user = req.context["auth_user"]
        cursor = self.db_session.query(Business).filter(Business.owner_id == current_user.id)
        cursor = cursor.order_by(Business.name.asc())

        business = list()

        for b in cursor.all():
            business.append(b.json_model)

        resp.media = business

        resp.status = falcon.HTTP_200


@falcon.before(requires_auth)
class ResourceBusinessUploadPhoto(DAMCoreResource):
    def on_post(self, req, resp, *args, **kwargs):
        super(ResourceBusinessUploadPhoto, self).on_post(req, resp, *args, **kwargs)

        current_user = req.context["auth_user"]

        # Get the file from form
        name = req.get_param("name")

        if name is not None:

            business = self.db_session.query(Business).filter(Business.name == name, Business.owner_id == current_user.id).one_or_none()
            
            if business is not None:

                resource_path = business.photo_path

                # Get the file from form
                incoming_file = req.get_param("image_file")

                # Run the common part for storing
                filename = utils.save_static_media_file(incoming_file, resource_path)

                # Update db model
                business.photo = filename
                # self.db_session.add(business)
                self.db_session.commit()

        resp.status = falcon.HTTP_200


@falcon.before(requires_auth)
class ResourceBusinessUpdate(DAMCoreResource):
    def on_put(self, req, resp, *args, **kwargs):
        super(ResourceBusinessUpdate, self).on_put(req, resp, *args, **kwargs)

        try:
            business_id = req.media["id"]
            name = req.media["name"]
            business_type = req.media["type"]
            definition = req.media["definition"]
            web = req.media["web"]
            facebook = req.media["facebook"]
            instagram = req.media["instagram"]
            twitter = req.media["twitter"]

            if business_id is not None:
                business = self.db_session.query(Business).filter(Business.id == business_id).one_or_none()
                aux = self.db_session.query(Business).filter(Business.name == name).one_or_none()

                if business is not None:
                    if aux is None or aux.id == business.id:
                        business.name = name
                        business.type = business_type
                        business.definition = definition
                        business.web = web
                        business.facebook = facebook
                        business.instagram = instagram
                        business.twitter = twitter
                        self.db_session.commit()
                    else:
                        raise falcon.HTTPBadRequest("Aquest nom ja existeix")
                else:
                    raise falcon.HTTPBadRequest("Aquest usuari no existeix")
            else:
                raise falcon.HTTPBadRequest("Necessito la id")
        except KeyError:
            raise falcon.HTTPBadRequest("El body ha de contenir la informació necessaria")

        resp.status = falcon.HTTP_200

