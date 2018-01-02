package models;

import orm.Model;
import orm.annotations.Column;
import orm.annotations.Entity;
import orm.fields.ForeignKey;
import orm.fields.StringField;
import orm.fields.TimeStampField;

import java.time.LocalDateTime;

@Entity(table = "expositions")
public class Exposition extends Model {
    private StringField name = new StringField(false);

    @Column(name = "showroom_id")
    private ForeignKey<Showroom> place = new ForeignKey<>(Showroom.class, false);
    private TimeStampField begins = new TimeStampField(false);
    private TimeStampField ends = new TimeStampField(false);

    public Exposition(
            String name,
            Showroom place,
            LocalDateTime begins,
            LocalDateTime ends
    ) {
        this.name.setValue(name);
        this.place.setValue(place);
        this.begins.setValue(begins);
        this.ends.setValue(ends);
    }

    public Exposition() {
    }

    public StringField getName() {
        return name;
    }

    public ForeignKey<Showroom> getPlace() {
        return place;
    }

    public TimeStampField getBegins() {
        return begins;
    }

    public TimeStampField getEnds() {
        return ends;
    }
}
