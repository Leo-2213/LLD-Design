package LLDDesigns.ATM.Model;

import LLDDesigns.ATM.Enum.ATMStatus;

public class ATM {
    private final  int id;
    private ATMStatus atmStatus;
    private int cashAvailable;
    private int oneHundredCount;
    private int twoHundredCount;
    private int fiveHundredCount;

    public ATM(ATMBuilder atmBuilder) {
        this.id = atmBuilder.id;
        this.atmStatus = ATMStatus.IDLE;
        this.cashAvailable = atmBuilder.cashAvailable;
        this.fiveHundredCount = atmBuilder.fiveHundredCount;
        this.twoHundredCount = atmBuilder.twoHundredCount;
        this.oneHundredCount = atmBuilder.oneHundredCount;
    }
    public int getId() {
        return id;
    }

    public ATMStatus getAtmStatus() {
        return atmStatus;
    }

    public int getCashAvailable() {
        return cashAvailable;
    }
    public int getTwoHundredCount() {
        return this.twoHundredCount;
    }

    public void setAtmStatus(ATMStatus atmStatus) {
        this.atmStatus = atmStatus;
    }

    public void setCashAvailable(int cashAvailable) {
        this.cashAvailable = cashAvailable;
    }


    public int getFiveHundredCount() {
        return this.fiveHundredCount;
    }

    public int getOneHundredCount() {
        return this.oneHundredCount;
    }
    public void setOneHundredCount(int oneHundredCount) {
        this.oneHundredCount = oneHundredCount;
    }

    public void setTwoHundredCount(int twoHundredCount) {
        this.twoHundredCount = twoHundredCount;
    }

    public void setFiveHundredCount(int fiveHundredCount) {
        this.fiveHundredCount = fiveHundredCount;
    }

    public static class ATMBuilder{
        private   int id;
        private int cashAvailable;
        private int oneHundredCount;
        private int twoHundredCount;
        private int fiveHundredCount;

        public ATMBuilder id(int id){
            this.id = id;
            return this;
        }
        public ATMBuilder cashAvailable(int cashAvailable){
            this.cashAvailable = cashAvailable;
            return this;
        }
        public ATMBuilder oneHundredCount(int oneHundredCount){
            this.oneHundredCount = oneHundredCount;
            return this;
        }
        public ATMBuilder twoHundredCount(int twoHundredCount){
            this.twoHundredCount = twoHundredCount;
            return this;
        }
        public ATMBuilder fiveHundredCount(int fiveHundredCount){
            this.fiveHundredCount = fiveHundredCount;
            return this;
        }

        public ATM build(){
            return new ATM(this);
        }
    }


}
