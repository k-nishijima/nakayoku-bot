import scala.collection.JavaConversions._
import org.atilika.kuromoji._

/**
  * 入力メッセージを解析するメッセージクラス
  *
  */
case class InputMessage(msg: String) {
  if (msg == null) throw new IllegalArgumentException

  val MYNAME = "@hogebot"
  val tokenizer = Tokenizer.builder().build()

  /** メッセージを形態素解析して返す */
  def tokens:java.util.List[org.atilika.kuromoji.Token] = {
    tokenizer.tokenize(msg);
  }

  /** 入力文か質問文かを判断する */
  def question:Boolean = {
//    for (token <- this.tokens) println(token.getSurfaceForm +","+ token.getAllFeaturesArray.mkString("|"))

    val direct = this.tokens.exists(token =>
        token.getAllFeaturesArray()(0) == "動詞" &&
        token.getAllFeaturesArray()(5).contains("命令") &&
        (token.getAllFeaturesArray()(7).contains("オシエ") ||
          token.getAllFeaturesArray()(7).contains("クダサイ")
        ))

    val teach = this.tokens.exists(token =>
      token.getAllFeaturesArray().length > 8 &&
        token.getAllFeaturesArray()(0) == "動詞" &&
        token.getAllFeaturesArray()(7).contains("オシエ"))

    val sign = msg.last == '?' ||
      msg.last == '？' || msg.takeRight(3) == "教えて"

//    println("direct="+ direct +"/teach="+ teach +"/sign="+sign)

    return direct || teach && sign
  }

  /** メッセージから自分のbot名以外の英単語を返す */
  def englishWords:Iterator[String] = {
    val regex = """[\w|@]+""".r
    regex.findAllIn(msg) filter {
      _ != MYNAME
    }
  }

  /**
    * 評価文を取り出す。
    * ここでは単純に初出の助詞以降をすべて取り出すこととする
    */
  def evaluation:String = {
    val div = this.tokens filter { token =>
      token.getAllFeaturesArray()(0) == "助詞"
    }
    msg.substring(div.head.getPosition + 1)
  }

}
