import falcon
from sqlalchemy.orm.exc import NoResultFound

import messages
from db.models import Event, EventStatusEnum
from resources.base_resources import DAMCoreResource


class ResourceGetEvents(DAMCoreResource):
    def on_get(self, req, resp, *args, **kwargs):
        super(ResourceGetEvents, self).on_get(req, resp, *args, **kwargs)

        request_event_status = req.get_param("status", False)
        if request_event_status is not None:
            request_event_status = request_event_status.upper()
            if (len(request_event_status) != 1) or (
                    request_event_status not in [i.value for i in EventStatusEnum.__members__.values()]):
                raise falcon.HTTPInvalidParam(messages.event_status_invalid, "status")

        response_events = list()

        aux_events = self.db_session.query(Event)

        if request_event_status is not None:
            aux_events = \
                aux_events.filter(
                    Event.status == EventStatusEnum(request_event_status))

        if aux_events is not None:
            for current_event in aux_events.all():
                response_events.append(current_event.json_model)

        resp.media = response_events
        resp.status = falcon.HTTP_200


class ResourceGetEvent(DAMCoreResource):
    def on_get(self, req, resp, *args, **kwargs):
        super(ResourceGetEvent, self).on_get(req, resp, *args, **kwargs)

        if "id" in kwargs:
            try:
                response_event = self.db_session.query(Event).filter(Event.id == kwargs["id"]).one()

                resp.media = response_event.json_model
                resp.status = falcon.HTTP_200
            except NoResultFound:
                raise falcon.HTTPBadRequest(description=messages.event_doesnt_exist)
        else:
            raise falcon.HTTPMissingParam("id")