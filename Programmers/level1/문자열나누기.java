class 문자열나누기 {
    public int solution(String s) {
        int answer = 1;
        int len = s.length();
        char c = s.charAt(0);
        int idx = 1;
        int xCnt = 1;
        int oCnt = 0;

        while(idx < len){
            char cur = s.charAt(idx);
            if(c == cur) xCnt++;
            else oCnt++;
            idx++;
            if(idx == len) {
                break;
            }
            if(xCnt == oCnt){
                c = s.charAt(idx);
                xCnt = 1;
                oCnt = 0;
                idx++;
                answer++;
            }
            
        }
        return answer;
    }
}