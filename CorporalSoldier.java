public class CorporalSoldier extends Soldier {
    public CorporalSoldier(General general) {
        super(2, general,"corporal");
    }

    @Override
    protected void executeAdvance(Boolean canAdvance) {
        if(canAdvance){
            getGeneral().addSoldier(new CapitanSoldier(getGeneral()));
            getGeneral().removeSoldier(this);
        }
    }
}
