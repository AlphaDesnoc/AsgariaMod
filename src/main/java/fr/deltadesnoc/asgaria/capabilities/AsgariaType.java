package fr.deltadesnoc.asgaria.capabilities;

public enum  AsgariaType {

    RUNE(0,1,2);

    int[] validSlots;

    AsgariaType(int... validSlots) {
        this.validSlots = validSlots;
    }

    public boolean hasSlot(int slot) {
        for (int s : this.validSlots) {
            if (s == slot)
                return true;
        }
        return false;
    }

    public int[] getValidSlots() {
        return this.validSlots;
    }
}
