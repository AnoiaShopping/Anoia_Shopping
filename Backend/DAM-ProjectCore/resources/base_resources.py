#!/usr/bin/python
# -*- coding: utf-8 -*-

import logging

mylogger = logging.getLogger(__name__)


# noinspection PyMethodMayBeStatic,PyUnusedLocal
class DAMCoreResource(object):
    def __print_request(self, request):
        mylogger.debug("New request {} {}?{} from host: {}".format(request.method, request.path, request.query_string,
                                                                   request.access_route))

    def __init__(self):
        self.db_session = None

    def on_get(self, req, resp, *args, **kwargs):
        self.__print_request(req)

    def on_post(self, req, resp, *args, **kwargs):
        self.__print_request(req)

    def on_put(self, req, resp, *args, **kwargs):
        self.__print_request(req)

    def on_head(self, req, resp, *args, **kwargs):
        self.__print_request(req)
