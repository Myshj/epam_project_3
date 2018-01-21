package utils;

import orm.Model;
import orm.fields.*;
import utils.meta.annotations.EntityNames;
import utils.meta.annotations.Relatives;

@EntityNames(singular = "testEntity", plural = "testEntities")
public class TestEntity extends Model {
    public static enum TestEnum {
        CASE_1,
        CASE_2
    }

    public TestEntity() {
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    public StringField stringField = new StringField(false);
    public IntegerField integerField = new IntegerField(false);
    public DecimalField decimalField = new DecimalField(false);
    public EnumField<TestEnum> enumField = new EnumField<>(TestEnum.class, false);
    public TimeStampField timeStampField = new TimeStampField(false);

    @Relatives(pluralName = "firstRelatives")
    public ForeignKey<TestEntity> relative = new ForeignKey<>(TestEntity.class, true);
}
