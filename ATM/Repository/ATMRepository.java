package LLDDesigns.ATM.Repository;

import LLDDesigns.ATM.Model.ATM;

import java.util.HashMap;
import java.util.Map;

public class ATMRepository {
    private final Map<String, ATM> atms = new HashMap<>();
    public void save(String atmId, ATM atm){
        atms.put(atmId, atm);
    }
    public ATM getById(String atmId){
        return atms.get(atmId);
    }

}
