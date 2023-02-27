@SuppressWarnings("unchecked")
public class Vertex<V extends Comparable<V>> implements Comparable<Vertex<V>> {

    V value;

    public Vertex(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        return value == ((Vertex<V>) obj).value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Vertex<V> v) {
        return Integer.compare(this.value.compareTo(v.value), 0);
    }
}