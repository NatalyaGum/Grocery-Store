package by.edu.webstore.entity;

import java.io.Serializable;

public abstract class AbstractEntity implements Serializable, Cloneable {
    /*private long id;

    public AbstractEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        int first = 31;
        int result = 1;
        result = result * first + (int) id;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(getClass().getSimpleName());
        result.append("[ id = ").append(id);
        return result.toString();
    }*/
}

