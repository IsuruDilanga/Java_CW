public class CircularQueue {

    private int front;
    private int end;
    private String[] queueArray;

    public CircularQueue(int size){
        queueArray = new String[size];
        front = -1;
        end = 0;
    }

    public void EnQueue(String name){
        if (front == 0 && end == queueArray.length -1 || (end == (front - 1) % (queueArray.length - 1))){
            System.out.print("Queue is full: ");
        } else if (front == -1) {
            front = end = 0;
            queueArray[end] = name;
        } else if (end == queueArray.length-1 && front != 0){
            end = 0;
            queueArray[end] = name;
        } else  {
            end++;
            queueArray[end] = name;
        }
    }

    public String DeQueue(){
        if (front == -1){
            System.out.println("Queue is empty");
        }

        String data = queueArray[front];
        queueArray[front] = String.valueOf(-1);
        if (front == end) {
            front = -1;
            end = -1;
        } else if (front == queueArray.length-1){
            front = 0;
        } else {
            front++;
        }
        return data;
    }

    public String firstName(){
        return queueArray[front];
    }

    public String endName(){
        return queueArray[end];
    }

}
