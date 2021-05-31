#!/usr/bin/python
# -*- coding: utf-8 -*-

import logging.config

import falcon

import messages
import middlewares
from falcon_multipart.middleware import MultipartMiddleware
from resources import account_resources, common_resources, user_resources, event_resources, business_resources
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

application.add_route("/account/profile", account_resources.ResourceAccountUserProfile()) # per veure l'usuari
application.add_route("/account/profile/update_profile_image", account_resources.ResourceAccountUpdateProfileImage()) # canviar imatge de perfil
application.add_route("/account/create_token", account_resources.ResourceCreateUserToken()) # crear token / iniciar sessió a nou dispositiu
application.add_route("/account/delete_token", account_resources.ResourceDeleteUserToken()) # eliminar token / tancar sessió

application.add_route("/account/recovery", account_resources.ResourceAccountRecovery()) #acc verification
application.add_route("/account/update_password", account_resources.ResourceUpdatePassword()) #acc verification


application.add_route("/users/register", user_resources.ResourceRegisterUser()) # crear usuari
application.add_route("/users/show/{username}", user_resources.ResourceGetUserProfile()) # visualitzar usuari específic

application.add_route("/events", event_resources.ResourceGetEvents())
application.add_route("/events/show/{id:int}", event_resources.ResourceGetEvent())

application.add_route("/business/create", business_resources.ResourceCreateBusiness()) # crear botiga / negoci
application.add_route("/business", business_resources.ResourceGetBusiness()) # Per llistar els negocis
application.add_route("/business/uploadphoto/{id:int}", business_resources.ResourceBusinessUploadPhoto()) # Per pujar la foto

##application.add_route("/business/profile", business_resources.ResourceBusinessProfile()) ## TODO: per implementar
##application.add_route("/business/edit", business_resources.ResourceEditProfile()) ## TODO: per implementar
##application.add_route("/business/delete", business_resources.ResourceDeleteProfile()) ## TODO: per implementar

application.add_sink(handle_404, "")
