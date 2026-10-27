class FoodFactory {
    public Food getFood(String order) {
        if (order.equalsIgnoreCase("pizza")) {
            return new Pizza();
        } else if (order.equalsIgnoreCase("cake")) {
            return new Cake();
        }
        return null;
    }
}