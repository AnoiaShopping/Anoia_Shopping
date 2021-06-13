#!/usr/bin/python
# -*- coding: utf-8 -*-

import base64
import datetime
import logging
import os
import random
import string
import smtplib

import falcon
from falcon.media.validators import jsonschema

import messages
import settings
from db.models import User, UserToken
from hooks import requires_auth
from resources import utils
from resources.base_resources import DAMCoreResource
from resources.schemas import SchemaUserToken
from settings import STATIC_DIRECTORY
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from sqlalchemy.orm.exc import NoResultFound

from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from email.mime.image import MIMEImage
from email.mime.base import MIMEBase
from email import encoders



from jinja2 import Environment 

mylogger = logging.getLogger(__name__)


class ResourceCreateUserToken(DAMCoreResource):
    def on_post(self, req, resp, *args, **kwargs):
        super(ResourceCreateUserToken, self).on_post(req, resp, *args, **kwargs)

        basic_auth_raw = req.get_header("Authorization")
        if basic_auth_raw is not None:
            basic_auth = basic_auth_raw.split()[1]
            auth_username, auth_password = (base64.b64decode(basic_auth).decode("utf-8").split(":"))
            if (auth_username is None) or (auth_password is None) or (auth_username == "") or (auth_password == ""):
                raise falcon.HTTPUnauthorized(description=messages.username_and_password_required)
        else:
            raise falcon.HTTPUnauthorized(description=messages.authorization_header_required)

        current_user = self.db_session.query(User).filter(User.email == auth_username).one_or_none()
        if current_user is None:
            current_user = self.db_session.query(User).filter(User.username == auth_username).one_or_none()

        if (current_user is not None) and (current_user.check_password(auth_password)):
            current_token = current_user.create_token()
            try:
                self.db_session.commit()
                resp.media = {"token": current_token.token}
                resp.status = falcon.HTTP_200
            except Exception as e:
                mylogger.critical("{}:{}".format(messages.error_saving_user_token, e))
                self.db_session.rollback()
                raise falcon.HTTPInternalServerError()
        else:
            raise falcon.HTTPUnauthorized(description=messages.user_not_found)


@falcon.before(requires_auth)
class ResourceDeleteUserToken(DAMCoreResource):
    @jsonschema.validate(SchemaUserToken)
    def on_post(self, req, resp, *args, **kwargs):
        super(ResourceDeleteUserToken, self).on_post(req, resp, *args, **kwargs)

        current_user = req.context["auth_user"]
        selected_token_string = req.media["token"]
        selected_token = self.db_session.query(UserToken).filter(UserToken.token == selected_token_string).one_or_none()

        if selected_token is not None:
            if selected_token.user.id == current_user.id:
                try:
                    self.db_session.delete(selected_token)
                    self.db_session.commit()

                    resp.status = falcon.HTTP_200
                except Exception as e:
                    mylogger.critical("{}:{}".format(messages.error_removing_user_token, e))
                    raise falcon.HTTPInternalServerError()
            else:
                raise falcon.HTTPUnauthorized(description=messages.token_doesnt_belongs_current_user)
        else:
            raise falcon.HTTPUnauthorized(description=messages.token_not_found)


@falcon.before(requires_auth)
class ResourceAccountUserProfile(DAMCoreResource):
    def on_get(self, req, resp, *args, **kwargs):
        super(ResourceAccountUserProfile, self).on_get(req, resp, *args, **kwargs)

        current_user = req.context["auth_user"]

        resp.media = current_user.json_model
        resp.status = falcon.HTTP_200


@falcon.before(requires_auth)
class ResourceAccountUpdateProfileImage(DAMCoreResource):
    def on_post(self, req, resp, *args, **kwargs):
        super(ResourceAccountUpdateProfileImage, self).on_post(req, resp, *args, **kwargs)

        # Get the user from the token
        current_user = req.context["auth_user"]
        resource_path = current_user.photo_path

        # Get the file from form
        incoming_file = req.get_param("image_file")

        # Run the common part for storing
        filename = utils.save_static_media_file(incoming_file, resource_path)

        # Update db model
        current_user.photo = filename
        self.db_session.add(current_user)
        self.db_session.commit()

        resp.status = falcon.HTTP_200
   
   
class ResourceAccountRecovery(DAMCoreResource):
    def on_post(self, req, resp, *args, **kwargs):
        super(ResourceAccountRecovery, self).on_post(req, resp, *args, **kwargs)
        email = req.media["email"]
        code = ''.join(random.choices(string.ascii_uppercase + string.digits,k=6))
        print(email)
        try:
            aux_user = self.db_session.query(User).filter(User.email == email).one_or_none()
            aux_user.recovery_code = code
            self.db_session.add(aux_user)
            self.db_session.commit()
            print(aux_user.json_model)
            #send mail
            smpt_server = "smtp.gmail.com"
            sender_email = "anoia.shopping.tic@gmail.com"
            password = "myzhvpeedjgdtqgo"
            
            html = """\

            <html>
            <head></head>
            <body>
                <p>Hi!<br>
                Your requested code to recover your account is:<br>
                """ +str(code)+ """
                </p>
            </body>
            </html>
            """
           
            message = MIMEMultipart('alternative')
            message["Subject"]:'[ANOIA-SHOPPING] Recovery account instructions'
            message["From"]:sender_email
            message["To"]:email

            image = "resources/images/Anoia-Shopping-1.png"
            logo = os.path.join(os.getcwd(),image)
            
            # to add an attachment is just add a MIMEBase object to read a picture locally.
            with open(logo, 'rb') as f:
                # set attachment mime and file name, the image type is png
                mime = MIMEBase('image', 'png', filename='logo')
                # add required header data:
                mime.add_header('Content-Disposition', 'attachment', filename='logo')
                mime.add_header('X-Attachment-Id', '0')
                mime.add_header('Content-ID', '<0>')
                # read attachment file content into the MIMEBase object
                mime.set_payload(f.read())
                # encode with base64
                encoders.encode_base64(mime)
      
            message.attach(MIMEText(html,"html"))
            
            try:
                print(message.as_string())
                server = smtplib.SMTP_SSL(smpt_server, 465)
                server.login(sender_email,password)
                server.sendmail(sender_email, email, message.as_string())
                server.quit()
            except Exception as e:
                print(e)
            
            
        except NoResultFound:
            resp.status = falcon.HTTP_200
        resp.status = falcon.HTTP_200


class ResourceUpdatePassword(DAMCoreResource):
    def on_post(self, req, resp, *args, **kwargs):
        super(ResourceUpdatePassword, self).on_post(req, resp, *args, **kwargs)
        
        email = req.media["email"] 
        password = req.media["password"]
        code = req.media["code"]
        
        try:
            user = self.db_session.query(User).filter(User.email==email, User.recovery_code==code).one()  # accedim a la informacio del user
            user.password = password
            user.recovery_code = code
            self.db_session.commit()

        except Exception as e:
            print(e)
        
        resp.status = falcon.HTTP_200


@falcon.before(requires_auth)
class ResourceAccountUpdateProfile(DAMCoreResource):
    def on_put(self, req, resp, *args, **kwargs):
        super(ResourceAccountUpdateProfile, self).on_put(req, resp, *args, **kwargs)

        try:
            account_id = req.media["id"]
            username = req.media["username"]
            name = req.media["name"]
            surname = req.media["surname"]
            email = req.media["email"]

            if account_id is not None:
                user = self.db_session.query(User).filter(User.id == account_id).one_or_none()

                if user is not None:
                    user.username = username
                    user.name = name
                    user.surname = surname
                    user.email = email
                    self.db_session.commit()

                else:
                    raise falcon.HTTPBadRequest("Aquest usuari no existeix")
            else:
                raise falcon.HTTPBadRequest("Necessito la id")
        except KeyError:
            raise falcon.HTTPBadRequest("El body ha de contenir la informació necessaria")

        resp.status = falcon.HTTP_200

