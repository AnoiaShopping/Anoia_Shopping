#!/usr/bin/python
# -*- coding: utf-8 -*-

import logging.config
import os

import sqlalchemy_utils

# Database settings
if "DAMCore_DB_HOST" in os.environ:
    DB_HOST = os.environ["DAMCore_DB_HOST"]
else:
    DB_HOST = "127.0.0.1"


DB_PORT = "3306"
DB_NAME = "dev-test"
DB_USERNAME = "dev-user"
DB_PASSWORD = "1234"
DB_ENCODING = "utf8"

# i18n settings
DEFAULT_LANGUAGE = "en"
CURRENT_LANGUAGE = None
ACCEPTED_LANGUAGES = {"en": "en", "es": "es_ES", "es-ES": "es_ES", "ca-ES": "ca_ES"}
LOCALE_DOMAIN = "damcore"
LOCALE_DIRECTORY = os.path.join(os.path.abspath(os.path.dirname(__file__)), "..", "resources", "locale")

# Date and time settings
DATE_DEFAULT_FORMAT = "%Y-%m-%d"
TIME_DEFAULT_FORMAT = "%H:%M:%S"
DATETIME_DEFAULT_FORMAT = "{date} {time}".format(date=DATE_DEFAULT_FORMAT, time=TIME_DEFAULT_FORMAT)

# Misc settings
MAX_USER_TOKENS = 5

# Static files settings
STATIC_HOSTNAME = "127.0.0.1:8001"
STATIC_DIRECTORY = os.path.join(os.path.abspath(__file__), "../../static", )
STATIC_URL = "static/"
MEDIA_PREFIX = "media/"
DEFAULT_IMAGE_NAME = "default.png"

# Logging settings
LOGGING_CONFIG = {
    "version": 1,
    "disable_existing_loggers": False,
    "formatters": {
        "default": {
            "format": "[%(levelname)s] [%(asctime)s] [%(module)s.%(funcName)s]: %(message)s"
        },
    },
    "handlers": {
        "console": {
            "level": "INFO",
            "class": "logging.StreamHandler",
            "formatter": "default",
        },
        "rotate_file": {
            "level": "DEBUG",
            "formatter": "default",
            "class": "logging.handlers.RotatingFileHandler",
            "filename": "../logs/damcore.log",
            "encoding": "utf8",
            "maxBytes": 1048576,
            "backupCount": 1,
        },
    },
    "loggers": {
        "": {
            "level": "DEBUG",
            "handlers": ["console", "rotate_file"],
            "propagate": True,
        },
    }
}


def get_current_language():
    return CURRENT_LANGUAGE


def get_accepted_languages():
    locales_list = list()
    for language in set(ACCEPTED_LANGUAGES.values()):
        locales_list.append(language)

    return locales_list


sqlalchemy_utils.i18n.get_locale = get_current_language


def configure_logging():
    logging.config.dictConfig(LOGGING_CONFIG)
