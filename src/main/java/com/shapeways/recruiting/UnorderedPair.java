package com.shapeways.recruiting;

public class UnorderedPair<L,R> {

		private final L left;
		private final R right;
		
		public UnorderedPair(L left, R right) {
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
			UnorderedPair other = (UnorderedPair) obj;
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
			return "UnorderedPair [left=" + left + ", right=" + right + "]";
		}

		public L getLeft() {
			return left;
		}

		public R getRight() {
			return right;
		}
		
		
}
