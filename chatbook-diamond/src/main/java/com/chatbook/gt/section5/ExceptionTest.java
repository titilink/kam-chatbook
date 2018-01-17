package com.chatbook.gt.section5;

/**
 * 具体来讲，当程序发生异常，我们可以采取以下动作响应：
 *   1、捕捉并处理异常，防止它传播给调用端；进而遏制异常的进一步传播。
 *   2、捕捉，并再次原样抛出异常给调用端，将异常原样throw。
 *   3、捕捉，然后抛出一个新异常给调用端，确保新抛出的异常包含原有异常信息，但可进一步封装，调用throw将新异常 抛出。
 *   4、不捕捉，任由它传播给调用端，并且该函数会马上中断，不会继续执行该函数的后续语句。
 * <p>
 *
 * 上层调用函数捕捉到异常后，可以对异常进行封装（如上例中第3、4种构造函数的方式）后再抛出，
 * 这样后续调用函数所获得的异常信息就不会丢失，进而就能获得产生异常的根本原因，以便程序员解决问题或反馈给使用用户。
 * <p>
 *
 * 1、异常需要封装和传递，对待异常，我们不要“吞噬”异常，也不要直接抛出异常，可采取一个异常容器对代码执行过程中抛出的异常进行收集，
 *    最后反馈给调用端，如此就不会丢弃异常，方便用户获取产生异常的根本原因。该技术常用于一个函数可能会抛出多种异常的情况。
 * 2、异常链也是一种传递异常的实用方法，该技术常用于有一定函数调用深度的业务场景。
 * <p>
 *
 * @author ganting
 * @date 2018-01-16
 * @since v1.0
 */
public class ExceptionTest {



}
