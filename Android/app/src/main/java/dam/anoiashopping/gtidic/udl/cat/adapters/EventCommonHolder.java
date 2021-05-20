package dam.anoiashopping.gtidic.udl.cat.adapters;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.models.Event;
import dam.anoiashopping.gtidic.udl.cat.models.EventStatus;
import dam.anoiashopping.gtidic.udl.cat.models.EventType;

public class EventCommonHolder {

    private static final String TAG = "EventCommonHolder";
    private TextView eventName;
    private TextView eventDescription;
    private ImageView eventPoster;
    private ImageView eventType;
    private TextView eventStatus;
    private TextView eventStatusColor;
    private TextView distanceToEvent;

    public EventCommonHolder(@NonNull View itemView) {

        eventName = itemView.findViewById(R.id.eventNameInfo);
        eventDescription = itemView.findViewById(R.id.eventDescriptionInfo);
        eventPoster = itemView.findViewById(R.id.eventPosterInfo);
        eventStatus = itemView.findViewById(R.id.eventStatusInfo);
        eventStatusColor = itemView.findViewById(R.id.eventStatusColourInfo);
        eventType = itemView.findViewById(R.id.eventTypeIconInfo);
        distanceToEvent = itemView.findViewById(R.id.eventDistanceToInfo);
    }

    public void bindHolder(Event e) {

        Log.d(TAG, "bindHolder() -> Event: " + e);

        this.eventName.setText(e.getName());
        this.eventDescription.setText(e.getDescription());
        this.eventStatus.setText(e.getStatus().name());

        this.eventStatusColor.setBackground(ContextCompat.getDrawable(
                this.eventStatusColor.getContext(),
                EventStatus.getColourResource(e.getStatus())));

        this.eventStatus.setText(e.getStatus().getName());
        this.eventStatus.setTextColor(ContextCompat.getColor(
                this.eventStatus.getContext(),
                EventStatus.getColourResource(e.getStatus())));

        Log.d(TAG, "onBindViewHolder() -> cEvent: " + e.getPoster_url());
        Picasso.get().load(e.getPoster_url()).into(this.eventPoster);

        this.eventType.setImageResource(EventType.getImageResource(e.getType()));
    }

}
