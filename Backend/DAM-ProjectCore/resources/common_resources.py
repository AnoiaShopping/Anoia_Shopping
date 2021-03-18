#!/usr/bin/python
# -*- coding: utf-8 -*-

import logging

import falcon
from sqlalchemy.orm import joinedload
from sqlalchemy.orm.exc import NoResultFound

import messages
from resources.base_resources import DAMCoreResource

mylogger = logging.getLogger(__name__)


class ResourceHome(DAMCoreResource):
    def on_get(self, req, resp, *args, **kwargs):
        super(ResourceHome, self).on_get(req, resp, *args, **kwargs)

        resp.media = messages.welcome_message
        resp.status = falcon.HTTP_200

