class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        Map<Character, Character> matchMap = new HashMap<>();
        matchMap.put(')', '(');
        matchMap.put('}', '{');
        matchMap.put(']', '[');

        for (char c : s.toCharArray()) {
            if (!matchMap.containsKey(c)) {

                stack.push(c);
            } else {
                if (stack.isEmpty() || stack.peek() != matchMap.get(c)) {
                    return false;
                }
                stack.pop();
            }
        }

        return stack.isEmpty();
    }
}