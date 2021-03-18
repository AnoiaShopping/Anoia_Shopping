#!/usr/bin/python
# -*- coding: utf-8 -*-

from sqlalchemy import create_engine
from sqlalchemy.orm import scoped_session, sessionmaker

from settings import DB_USERNAME, DB_PASSWORD, DB_HOST, DB_PORT, DB_NAME, DB_ENCODING

DB_ENGINE = create_engine(
    "mysql+pymysql://{}:{}@{}:{}/{}?charset=utf8".format(DB_USERNAME, DB_PASSWORD, DB_HOST, DB_PORT, DB_NAME),
    encoding=DB_ENCODING, echo=False, pool_recycle=3600)
DB_SESSION_FACTORY = sessionmaker(bind=DB_ENGINE)
DB_SCOPED_SESSION_FACTORY = scoped_session(DB_SESSION_FACTORY)


def create_db_session():
    return DB_SESSION_FACTORY()
