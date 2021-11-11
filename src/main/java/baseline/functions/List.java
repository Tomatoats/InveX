package baseline.functions;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public class List implements ObservableList<List> {
    @Override
    public void addListener(ListChangeListener<? super List> listener) {

    }

    @Override
    public void removeListener(ListChangeListener<? super List> listener) {

    }

    @Override
    public boolean addAll(List... elements) {
        return false;
    }

    @Override
    public boolean setAll(List... elements) {
        return false;
    }

    @Override
    public boolean setAll(Collection<? extends List> col) {
        return false;
    }

    @Override
    public boolean removeAll(List... elements) {
        return false;
    }

    @Override
    public boolean retainAll(List... elements) {
        return false;
    }

    @Override
    public void remove(int from, int to) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<List> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(List list) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends List> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends List> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public List get(int index) {
        return null;
    }

    @Override
    public List set(int index, List element) {
        return null;
    }

    @Override
    public void add(int index, List element) {

    }

    @Override
    public List remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<List> listIterator() {
        return null;
    }

    @Override
    public ListIterator<List> listIterator(int index) {
        return null;
    }

    @Override
    public java.util.List<List> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }
}
