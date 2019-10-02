#include<iostream>

using namespace std;

/* Testing multi-line
 * comment.
 */
class Message
{
  public:

    void display() {
      cout << "Hello World\n";  // print hello world
    }
};

int main()
{
    Message c;
    c.display();

    return 0;
}