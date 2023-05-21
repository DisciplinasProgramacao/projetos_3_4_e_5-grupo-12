package business;

class Key<K1, K2> {
    public K1 key1;
    public K2 key2;

    public Key(K1 key1, K2 key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    public K1 getKey1() {
        return this.key1;
    }

    public K2 getKey2() {
        return this.key2;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Key<K1, K2> key = (Key) o;
        if (key1 != null ? !key1.equals(key.key1) : key.key1 != null) {
            return false;
        }

        if (key2 != null ? !key2.equals(key.key2) : key.key2 != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = key1 != null ? key1.hashCode() : 0;
        result = 31 * result + (key2 != null ? key2.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "[" + key1 + ", " + key2 + "]";
    }
}