#!/usr/bin/python
# -*- coding: utf-8 -*-

import logging.config

import falcon

import messages
import middlewares
from falcon_multipart.middleware import MultipartMiddleware
from resources import account_resources, common_resources, user_resources, event_resources, business_resources, \
    product_resources
from settings import configure_logging

# LOGGING
mylogger = logging.getLogger(__name__)
configure_logging()


# DEFAULT 404
# noinspection PyUnusedLocal
def handle_404(req, resp):
    resp.media = messages.resource_not_found
    resp.status = falcon.HTTP_404


# FALCON
app = application = falcon.API(
    middleware=[
        middlewares.DBSessionManager(),
        middlewares.Falconi18n(),
        MultipartMiddleware()
    ]
)

application.add_route("/", common_resources.ResourceHome())

application.add_route("/account/profile", account_resources.ResourceAccountUserProfile())
application.add_route("/account/profile/update_profile_image", account_resources.ResourceAccountUpdateProfileImage())
application.add_route("/account/create_token", account_resources.ResourceCreateUserToken())
application.add_route("/account/delete_token", account_resources.ResourceDeleteUserToken())
application.add_route("/account/update", account_resources.ResourceAccountUpdateProfile())

application.add_route("/account/recovery", account_resources.ResourceAccountRecovery())
application.add_route("/account/update_password", account_resources.ResourceUpdatePassword())

application.add_route("/users/register", user_resources.ResourceRegisterUser())
application.add_route("/users/show/{username}", user_resources.ResourceGetUserProfile())

application.add_route("/events", event_resources.ResourceGetEvents())
application.add_route("/events/show/{id:int}", event_resources.ResourceGetEvent())

application.add_route("/business", business_resources.ResourceGetBusiness())
application.add_route("/business/own_business", business_resources.ResourceGetOwnBusiness())
application.add_route("/business/create", business_resources.ResourceCreateBusiness())
application.add_route("/business/uploadphoto", business_resources.ResourceBusinessUploadPhoto())
application.add_route("/business/update", business_resources.ResourceBusinessUpdate())

#  application.add_route("/business/profile", business_resources.ResourceBusinessProfile())
#  application.add_route("/business/edit", business_resources.ResourceEditProfile())
#  application.add_route("/business/delete", business_resources.ResourceDeleteProfile())

application.add_route("/product/create", product_resources.ResourcesCreateProduct())  # create POST
application.add_route("/product/show", product_resources.ResourcesFindProductByOwner())  # show GET

application.add_route("/product", product_resources.ResourcesGetProducts())  # show GET

application.add_sink(handle_404, "")
