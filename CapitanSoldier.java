public class CapitanSoldier extends Soldier{
    public CapitanSoldier(General general) {
        super(3, general, "capitan");
    }

    @Override
    protected void executeAdvance(Boolean canAdvance) {
        if(canAdvance){
            getGeneral().addSoldier(new MajorSoldier(getGeneral()));
            getGeneral().removeSoldier(this);
        }
    }
}
