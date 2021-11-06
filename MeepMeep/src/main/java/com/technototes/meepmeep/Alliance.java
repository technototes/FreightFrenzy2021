package com.technototes.meepmeep;

public enum Alliance {
    RED, BLUE;
    public static class Selector<T>{
        private final T r, b;
        protected Selector(T red, T blue){
            r = red;
            b = blue;
        }
        public T select(Alliance a){
            return a == RED ? r : b;
        }
        public static <T> Selector<T> of(T red, T blue){
            return new Selector<>(red, blue);
        }

        public static <T> T selectOf(Alliance alliance, T red, T blue){
            return Selector.of(red, blue).select(alliance);
        }
    }
}
