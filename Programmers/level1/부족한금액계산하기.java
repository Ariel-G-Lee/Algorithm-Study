class �����ѱݾװ���ϱ�{
    public long solution(long price, long money, long count) {
        long answer = 0;
        long total = price * (count * (count + 1) / 2) ;
        if(money < total) answer = total-money;
        else answer = 0;

        return answer;
    }
}