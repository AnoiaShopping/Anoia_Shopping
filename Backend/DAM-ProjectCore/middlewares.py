#!/usr/bin/python
# -*- coding: utf-8 -*-

import gettext
import logging

import db
import settings

mylogger = logging.getLogger(__name__)


# noinspection PyMethodMayBeStatic,PyUnusedLocal
class DBSessionManager(object):
    def __init__(self):
        self.db_factory = db.DB_SCOPED_SESSION_FACTORY

    def process_resource(self, req, resp, resource, params):
        resource.db_session = db.create_db_session()

    def process_response(self, req, resp, resource, req_succeeded):
        if (resource is not None) and (resource.db_session is not None):
            resource.db_session.close()


# noinspection PyMethodMayBeStatic,PyUnusedLocal
class Falconi18n(object):
    def process_request(self, req, resp):
        locale = None
        request_language = req.get_header("Accept-Language")
        if (request_language in settings.ACCEPTED_LANGUAGES) and (request_language != settings.DEFAULT_LANGUAGE):
            mylogger.debug("Setting language to: {}".format(settings.ACCEPTED_LANGUAGES[request_language]))
            locale = gettext.translation(settings.LOCALE_DOMAIN, localedir=settings.LOCALE_DIRECTORY,
                                         languages=[settings.ACCEPTED_LANGUAGES[request_language]])
            settings.CURRENT_LANGUAGE = settings.ACCEPTED_LANGUAGES[request_language]
        else:
            locale = gettext.NullTranslations()
            settings.CURRENT_LANGUAGE = settings.DEFAULT_LANGUAGE

        locale.install()
