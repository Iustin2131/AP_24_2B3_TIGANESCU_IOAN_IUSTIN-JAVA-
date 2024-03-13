class Church extends Attraction implements Visitable {
    private String openingHours;

    public Church(String name) {
        super(name);
    }

    @Override
    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    // Getter pentru orele de deschidere
    public String getOpeningHours() {
        return openingHours;
    }
}