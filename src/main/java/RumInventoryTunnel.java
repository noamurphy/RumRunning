import java.util.*;

/**
 A class to modify and keep track of doubly linked-list of Barrel objects.
 */
public class RumInventoryTunnel implements Tunnel{
    //Two tunnels to allow barrel placement at the 'middle' in O(1) time.
    private LinkedList<Integer> tunnel1 = new LinkedList<>();
    private LinkedList<Integer> tunnel2 = new LinkedList<>();

    @Override
    public void addA(int amtOfRum) {
        tunnel1.addFirst(amtOfRum);
    }

    @Override
    public int removeA() throws NoSuchElementException{
        try{
            if(size()==1) {
                if (tunnel1.size() == 1) {
                    return tunnel1.remove(0);
                } else {
                    return tunnel2.remove(0);
                }
            }
            else{
                return tunnel1.removeFirst();
            }
        }
        catch (NoSuchElementException e){
            throw e;
        }
    }

    @Override
    public void addB(int amtOfRum) {
        if((size() + 1)%2 == 1) {
            tunnel1.addLast(amtOfRum);
        }
        else {
            tunnel2.addFirst(amtOfRum);
        }
    }

    @Override
    public int removeB() throws NoSuchElementException{
        if (size() == 0){
            throw new NoSuchElementException();
        }
        else{
            if(size()==1) {
                if (tunnel1.size() == 1) {
                    return tunnel1.remove(0);
                } else {
                    return tunnel2.remove(0);
                }
            }
            else{
                if(size()%2 == 1){
                    return tunnel1.removeLast();
                }
                else{
                    return tunnel2.removeFirst();
                }
            }
        }
    }

    @Override
    public void addC(int amtOfRum) {
        tunnel2.addLast(amtOfRum);
    }

    @Override
    public int removeC() throws NoSuchElementException{
        try{
            if(size()==1) {
                if (tunnel1.size() == 1) {
                    return tunnel1.remove(0);
                }
                else {
                    return tunnel2.remove(0);
                }
            }
            else{
                return tunnel2.removeLast();
            }
        }
        catch(NoSuchElementException e){
            throw e;
        }
    }

    @Override
    public int size() {
        return tunnel1.size() + tunnel2.size();
    }

    @Override
    public int get(int index) throws NoSuchElementException {
        if (index >= 0 && index < size()) {
            if (index < tunnel1.size()) {
                return tunnel1.get(index);
            } else {
                return tunnel2.get(index);
            }
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public Iterator<Integer> iterator(){
        return new TunnelIterator<Integer>(tunnel1, tunnel2);
    }

    private class TunnelIterator<Integer> implements Iterator<Integer>{
        int iterator = 1; //Indicates which iterator is being used of 1 & 2
        Iterator<Integer> tunnel1Iterator;
        Iterator<Integer> tunnel2Iterator;
        int cursor;
        TunnelIterator(LinkedList<Integer> tunnel1, LinkedList<Integer> tunnel2){
            tunnel1Iterator = tunnel2.listIterator(0);
            tunnel2Iterator = tunnel1.listIterator(0);
        }
        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext(){
            if (size() < 2)
                return false;
            if (tunnel1Iterator.hasNext() != true) {
                if (tunnel2Iterator.hasNext() != true) {
                    return false;
                } else {
                    iterator = 2;
                    return true;
                }
            }
            else{
                return true;
            }
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Integer next() throws NoSuchElementException{
            if (this.hasNext()){
                if (iterator == 1){
                    return tunnel1Iterator.next();
                }
                else{
                    return tunnel2Iterator.next();
                }
            }
            else {
                throw new NoSuchElementException();
            }
        }
    }
}
