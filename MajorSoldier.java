public class MajorSoldier extends Soldier {
    public MajorSoldier(General general) {
        super(4, general, "major");
    }

    @Override
    protected void executeAdvance(Boolean canAdvance) {
        ;
    }
}
