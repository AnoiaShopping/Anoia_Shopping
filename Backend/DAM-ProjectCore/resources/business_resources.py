#!/usr/bin/python
# -*- coding: utf-8 -*-

import logging

import falcon
from falcon.media.validators import jsonschema
from sqlalchemy.exc import IntegrityError
from sqlalchemy.orm.exc import NoResultFound

import messages
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

        current_user=req.context["auth_user"]
        cursor = self.db_session.query(Business).filter(Business.owner_id != current_user.id)

        business = list()

        for b in cursor.all():
            business.append(b.json_model)

        resp.media = business

        resp.status = falcon.HTTP_200
