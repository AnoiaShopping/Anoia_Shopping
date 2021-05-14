package dam.anoiashopping.gtidic.udl.cat.models;

import com.google.gson.annotations.SerializedName;

public class Event {

        @SerializedName("id")

        private String id;

        @SerializedName("name")

        private String name;

        @SerializedName("description")

        private String description;

        @SerializedName("latitude")

        private double latitude;

        @SerializedName("longitude")

        private double longitude;

        @SerializedName("poster_url")

        private String poster_url;

        @SerializedName("start_date")

        private String start_date;

        @SerializedName("finish_date")

        private String finish_date;

        @SerializedName("type")

        private EventType type;

        @SerializedName("status")

        private EventStatus status;

        public Event() {

        }



        public String getId() {

            return id;

        }



        public void setId(String id) {

            this.id = id;

        }



        public String getName() {

            return name;

        }



        public void setName(String name) {

            this.name = name;

        }



        public String getDescription() {

            return description;

        }



        public void setDescription(String description) {

            this.description = description;

        }



        public double getLatitude() {

            return latitude;

        }



        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getPoster_url() {
            return poster_url;
        }

        public void setPoster_url(String poster_url) {
            this.poster_url = poster_url;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getFinish_date() {
            return finish_date;
        }

        public void setFinish_date(String finish_date) {
            this.finish_date = finish_date;
        }

        public EventType getType() {
            return type;
        }

        public void setType(EventType type) {
            this.type = type;
        }



        public EventStatus getStatus() {
            return status;
        }

        public void setStatus(EventStatus status) {
            this.status = status;
        }

        @Override
        public boolean equals(Object o) {

            // If the object is compared with itself then return true
            if (o == this) {
                return true;
            }

       /* Check if o is an instance of Complex or not
         "null instanceof [type]" also returns false */
            if (!(o instanceof Event)) {
                return false;
            }

            // typecast o to Complex so that we can compare data members
            Event e = (Event) o;

            // Compare the data members and return accordingly
            return this.name.equals(e.getName())
                    && this.latitude == e.getLatitude()
                    && this.longitude == e.getLatitude()
                    && this.start_date.equals(e.getStart_date())
                    && this.finish_date.equals(e.getFinish_date())
                    && this.description.equals(e.getDescription())
                    && this.poster_url.equals(e.getPoster_url())
                    && this.status == e.getStatus()
                    && this.type == e.getType();
        }

        @Override
        public String toString(){
            return "Type (id) " + this.type.id + " | Type (name) " + this.type.name +"\n"
                    + "Status (id) " + this.status.id + " | Status (name) " + this.status.name;
        }
}
