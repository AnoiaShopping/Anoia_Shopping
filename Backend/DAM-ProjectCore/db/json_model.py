#!/usr/bin/python
# -*- coding: utf-8 -*-

import abc
import datetime

import falcon

from settings import DATETIME_DEFAULT_FORMAT, DATE_DEFAULT_FORMAT, TIME_DEFAULT_FORMAT


class JSONModel(object):
    __metaclass__ = abc.ABCMeta

    def _create_json_model(self, **attributes):
        final_model = dict()
        try:
            for current_key in attributes.keys():
                aux_attribute = getattr(self, attributes[current_key])
                if isinstance(aux_attribute, JSONModel) and aux_attribute is not None:
                    final_model[current_key] = aux_attribute.json_model
                elif isinstance(aux_attribute, datetime.datetime):
                    final_model[current_key] = aux_attribute.strftime(DATETIME_DEFAULT_FORMAT)
                elif isinstance(aux_attribute, datetime.date):
                    final_model[current_key] = aux_attribute.strftime(DATE_DEFAULT_FORMAT)
                elif isinstance(aux_attribute, datetime.time):
                    final_model[current_key] = aux_attribute.strftime(TIME_DEFAULT_FORMAT)
                else:
                    final_model[current_key] = aux_attribute
            return final_model
        except KeyError as e:
            raise falcon.HTTPInternalServerError(description=str(e))

    @abc.abstractmethod
    def json_model(self):
        pass

    def to_json_model(self, **attributes):
        return self._create_json_model(**attributes)
