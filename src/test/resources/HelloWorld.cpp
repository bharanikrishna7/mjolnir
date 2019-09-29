#include<iostream>

using namespace std;

class Message
{
  public:

    void display() {
      cout << "Hello World\n";
    }
};

int main()
{
    Message c;    // Creating an object
    c.display();  // Calling the function

    return 0;
}