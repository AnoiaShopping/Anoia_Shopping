#!/usr/bin/python
# -*- coding: utf-8 -*-

import datetime
import logging
import os

from sqlalchemy.sql import text

import db
import settings
from db.models import SQLAlchemyBase, User, GenereEnum, UserToken, Event, EventTypeEnum
from settings import DEFAULT_LANGUAGE

# LOGGING
mylogger = logging.getLogger(__name__)
settings.configure_logging()


def execute_sql_file(sql_file):
    sql_folder_path = os.path.join(os.path.dirname(__file__), "sql")
    sql_file_path = open(os.path.join(sql_folder_path, sql_file), encoding="utf-8")
    sql_command = text(sql_file_path.read())
    db_session.execute(sql_command)
    db_session.commit()
    sql_file_path.close()


if __name__ == "__main__":
    settings.configure_logging()

    db_session = db.create_db_session()

    # -------------------- REMOVE AND CREATE TABLES --------------------
    mylogger.info("Removing database...")
    SQLAlchemyBase.metadata.drop_all(db.DB_ENGINE)
    mylogger.info("Creating database...")
    SQLAlchemyBase.metadata.create_all(db.DB_ENGINE)



    # -------------------- CREATE USERS --------------------
    mylogger.info("Creating default users...")
    # noinspection PyArgumentList
    user_admin = User(
        created_at=datetime.datetime(2020, 1, 1, 0, 1, 1),
        username="admin",
        email="admin@damcore.com",
        name="Administrator",
        surname="DamCore",
        genere=GenereEnum.male,
    )
    user_admin.set_password("DAMCoure")

    # noinspection PyArgumentList
    user_1= User(
        created_at=datetime.datetime(2020, 1, 1, 0, 1, 1),
        username="usuari1",
        email="usuari1@gmail.com",
        name="usuari",
        surname="1",
        birthdate=datetime.datetime(1989, 1, 1),
        genere=GenereEnum.male
    )
    user_1.set_password("a1s2d3f4")
    user_1.tokens.append(UserToken(token="656e50e154865a5dc469b80437ed2f963b8f58c8857b66c9bf"))

    # noinspection PyArgumentList
    user_2 = User(
        created_at=datetime.datetime(2020, 1, 1, 0, 1, 1),
        username="user2",
        email="user2@gmail.com",
        name="user",
        surname="2",
        birthdate=datetime.datetime(2017, 1, 1),
        genere=GenereEnum.male,
    )
    user_2.set_password("r45tgt")
    user_2.tokens.append(UserToken(token="0a821f8ce58965eadc5ef884cf6f7ad99e0e7f58f429f584b2"))

    db_session.add(user_admin)
    db_session.add(user_1)
    db_session.add(user_2)



    # -------------------- CREATE EVENTS --------------------

    day_period = datetime.timedelta(days=1)

    event_hackatoon = Event(
        created_at=datetime.datetime.now(),
        name="event1",
        description="description 1",
        type=EventTypeEnum.hackathon,
        start_date=datetime.datetime.now() + (day_period * 3),
        finish_date=datetime.datetime.now() + (day_period * 5),
        owner_id = 0,
        poster="logo.png",
        registered=[user_1, user_2]
    )

    event_livecoding = Event(
        created_at=datetime.datetime.now(),
        name="event2",
        description="descr2",
        type=EventTypeEnum.livecoding,
        start_date=datetime.datetime.now() - (day_period * 5),
        finish_date=datetime.datetime.now() - (day_period * 4),
        owner_id=1,
        registered=[user_2]
    )

    event_lanparty = Event(
        created_at=datetime.datetime.now(),
        name="event3",
        description="desc3",
        type=EventTypeEnum.lanparty,
        start_date=datetime.datetime.now(),
        finish_date=datetime.datetime.now() + (day_period * 1),
        owner_id=1,
        registered=[]
    )

    db_session.add(event_hackatoon)
    db_session.add(event_livecoding)
    db_session.add(event_lanparty)



    db_session.commit()
    db_session.close()
