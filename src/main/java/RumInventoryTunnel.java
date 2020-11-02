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
        return tunnel1.listIterator();
    }

    private class TunnelIterator{
        ListIterator<Integer> firstIterator = tunnel1.listIterator(0);
        ListIterator<Integer> secondIterator = tunnel2.listIterator(0);
        public void next(){
            if(firstIterator.hasNext()) {
            }
        }
    }
}
