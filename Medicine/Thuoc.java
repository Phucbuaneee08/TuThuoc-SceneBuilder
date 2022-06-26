    package Medicine;

    import javafx.beans.property.SimpleObjectProperty;

    import java.util.Date;


    public class Thuoc extends Product{


        public Thuoc(int productID, String name, int quantity, String link, String unit, Date expiredDate, String effect) {
            super(productID, name, quantity, link, unit);
            this.expiredDate = new SimpleObjectProperty<>(expiredDate);
            this.effect = effect;
        }

        private SimpleObjectProperty<Date> expiredDate;
        private String effect;
        private Person user;

        public SimpleObjectProperty<Date> getExpiredDate() {
            return expiredDate;
        }

        public void setExpiredDate(Date expiredDate) {
            this.expiredDate.set(expiredDate);
        }

        public String getEffect() {
            return effect;
        }

        public void setEffect(String effect) {
            this.effect = effect;
        }
        @Override
        public void showInfo(){
            super.showInfo();
            System.out.println("ExpiredDate: "+ this.getExpiredDate());
            System.out.println("Effect: "+ this.getEffect());
        }
    }
