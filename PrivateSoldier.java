public class PrivateSoldier extends Soldier{
    public PrivateSoldier(General general){
        super(1, general, "private");
    }
    @Override
    protected void executeAdvance(Boolean canAdvance) {
        if(canAdvance){
            getGeneral().addSoldier(new CorporalSoldier(getGeneral()));
            getGeneral().removeSoldier(this);
        }
    }
}
