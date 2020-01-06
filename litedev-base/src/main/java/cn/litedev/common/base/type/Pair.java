package cn.litedev.common.base.type;

/**
 * 引入一个简简单单的Pair, 用于返回值返回两个元素.
 * <p>
 *
 * @author copy from Twitter Common
 */
public class Pair<L, R> {

    private final L left;
    private final R right;

    /**
     * Creates a new pair.
     */
    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }


    public L getLeft() {
        return left;
    }


    public R getRight() {
        return right;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((left == null) ? 0 : left.hashCode());
        return prime * result + ((right == null) ? 0 : right.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pair<?, ?> other = (Pair<?, ?>) obj;
        if (left == null) {
            if (other.left != null) {
                return false;
            }
        } else if (!left.equals(other.left)) {
            return false;
        }
        if (right == null) {
            return other.right == null;
        }
        return right.equals(other.right);
    }

    @Override
    public String toString() {
        return "Pair [left=" + left + ", right=" + right + ']';
    }

    /**
     * 根据等号左边的泛型，自动构造合适的Pair
     */
    public static <L, R> Pair<L, R> of(L left, R right) {
        return new Pair<>(left, right);
    }
}
