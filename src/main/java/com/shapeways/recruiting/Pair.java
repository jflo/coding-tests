package com.shapeways.recruiting;

/**
 * Specifically, this is an unordered pair. The hashcode and equals methods have been implemented to disregard ordering.
 * foo,bar == bar,foo
 * @author florentj
 *
 * @param <L>
 * @param <R>
 */
public class Pair<L,R> {

		private final L left;
		private final R right;
		
		public Pair(L left, R right) {
			this.left = left;
			this.right = right;
		}

		@Override
		public int hashCode() {
			return left.hashCode() ^ right.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if(this.left.equals(other.left) && this.right.equals(other.right)) {
				return true;
			} else if(this.left.equals(other.right) && this.right.equals(other.left)){
				return true;
			} else {
				return false;
			}
		}

		@Override
		public String toString() {
			return left + "," + right;
		}

		public L getLeft() {
			return left;
		}

		public R getRight() {
			return right;
		}
		
		
}
