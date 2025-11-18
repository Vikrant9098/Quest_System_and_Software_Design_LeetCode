# Definition for doubly linked list node
class Node(object):
    def __init__(self, key=0, value=0):
        self.key = key
        self.value = value
        self.prev = None
        self.next = None

class LRUCache(object):

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.capacity = capacity
        self.map = {}  # key -> node
        self.head = Node()  # Dummy head
        self.tail = Node()  # Dummy tail
        self.head.next = self.tail
        self.tail.prev = self.head

    # Remove a node from the linked list
    def _remove(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

    # Insert a node right after head (most recent)
    def _insert(self, node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next.prev = node
        self.head.next = node

    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        if key not in self.map:
            return -1  # Not found
        node = self.map[key]
        self._remove(node)  # Remove from current position
        self._insert(node)  # Move to front (most recent)
        return node.value

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: None
        """
        if key in self.map:
            self._remove(self.map[key])  # Remove old node

        node = Node(key, value)
        self._insert(node)  # Insert new node at front
        self.map[key] = node

        if len(self.map) > self.capacity:
            lru = self.tail.prev  # Least recently used
            self._remove(lru)  # Remove from list
            del self.map[lru.key]  # Remove from map
