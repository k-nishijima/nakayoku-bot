/**

  import scala.collection.JavaConversions._
  import org.atilika.kuromoji._
  val tokenizer = Tokenizer.builder().build()
  
  val tokens = tokenizer.tokenize("ねえ @hogebot k_nishijimaさんについて教えてください");
  val tokens = tokenizer.tokenize("ねえ @hogebot k_nishijimaさんについて教えて？");

  val tokens = tokenizer.tokenize("ねえ @hogebot k_nishijimaさんは教えてるの？彼について教えて?");

    val tokens = tokenizer.tokenize("ねえ @hogebot k_nishijimaのこと教えろ?");

  val tokens = tokenizer.tokenize("@hogebot k_nishijimaさんは日本語を教えてる");

  for (token <- tokens) println(token.getAllFeaturesArray.mkString("|"))

  for (token <- tokens) println(token.getSurfaceForm +","+ token.getAllFeaturesArray.mkString("|"))
  

  英語は混ざっても読みが取れないだけか。
  「教えて？」の部分が興味深い。

ねえ,感動詞|*|*|*|*|*|ねえ|ネエ|ネー
 ,記号|空白|*|*|*|*|*
@,名詞|サ変接続|*|*|*|*|*
hogebot,名詞|固有名詞|組織|*|*|*|*
 ,記号|空白|*|*|*|*|*
k,名詞|固有名詞|組織|*|*|*|*
_,名詞|サ変接続|*|*|*|*|*
nishijima,名詞|固有名詞|組織|*|*|*|*
さん,名詞|接尾|人名|*|*|*|さん|サン|サン
について,助詞|格助詞|連語|*|*|*|について|ニツイテ|ニツイテ
教え,動詞|自立|*|*|一段|連用形|教える|オシエ|オシエ
て,助詞|接続助詞|*|*|*|*|て|テ|テ
ください,動詞|非自立|*|*|五段・ラ行特殊|命令ｉ|くださる|クダサイ|クダサイ

感動詞|*|*|*|*|*|ねえ|ネエ|ネー
記号|空白|*|*|*|*|*
名詞|サ変接続|*|*|*|*|*
名詞|固有名詞|組織|*|*|*|*
記号|空白|*|*|*|*|*
名詞|固有名詞|組織|*|*|*|*
名詞|サ変接続|*|*|*|*|*
名詞|固有名詞|組織|*|*|*|*
名詞|接尾|人名|*|*|*|さん|サン|サン
助詞|格助詞|連語|*|*|*|について|ニツイテ|ニツイテ
動詞|自立|*|*|一段|連用形|教える|オシエ|オシエ
助詞|接続助詞|*|*|*|*|て|テ|テ
記号|一般|*|*|*|*|？|？|？

ねえ,感動詞|*|*|*|*|*|ねえ|ネエ|ネー
 ,記号|空白|*|*|*|*|*
@,名詞|サ変接続|*|*|*|*|*
hogebot,名詞|固有名詞|組織|*|*|*|*
 ,記号|空白|*|*|*|*|*
k,名詞|固有名詞|組織|*|*|*|*
_,名詞|サ変接続|*|*|*|*|*
nishijima,名詞|一般|*|*|*|*|*
の,助詞|連体化|*|*|*|*|の|ノ|ノ
こと,名詞|非自立|一般|*|*|*|こと|コト|コト
教えろ,動詞|自立|*|*|一段|命令ｒｏ|教える|オシエロ|オシエロ
?,名詞|サ変接続|*|*|*|*|*



    val tokens = tokenizer.tokenize("@hogebot k_nishijimaは教えるのが上手");

名詞|サ変接続|*|*|*|*|*
名詞|固有名詞|組織|*|*|*|*
記号|空白|*|*|*|*|*
名詞|固有名詞|組織|*|*|*|*
名詞|サ変接続|*|*|*|*|*
名詞|一般|*|*|*|*|*
助詞|係助詞|*|*|*|*|は|ハ|ワ
動詞|自立|*|*|一段|基本形|教える|オシエル|オシエル
名詞|非自立|一般|*|*|*|の|ノ|ノ
助詞|格助詞|一般|*|*|*|が|ガ|ガ
名詞|形容動詞語幹|*|*|*|*|上手|ジョウズ|ジョーズ


  「は」が2回重なることもあるだろう。やっぱり先頭の助詞で切るのがいいのかな。
  val tokens = tokenizer.tokenize("@hogebot k_nishijimaはたいていは優しい");

  名詞|サ変接続|*|*|*|*|*
名詞|固有名詞|組織|*|*|*|*
記号|空白|*|*|*|*|*
名詞|固有名詞|組織|*|*|*|*
名詞|サ変接続|*|*|*|*|*
名詞|一般|*|*|*|*|*
助詞|係助詞|*|*|*|*|は|ハ|ワ
副詞|助詞類接続|*|*|*|*|たいてい|タイテイ|タイテイ
助詞|係助詞|*|*|*|*|は|ハ|ワ
形容詞|自立|*|*|形容詞・イ段|基本形|優しい|ヤサシイ|ヤサシイ


  他の助詞、例えば接続助詞の「て」はどうだろう。
  val tokens = tokenizer.tokenize("@hogebot westってばたいていは優しい");

名詞|サ変接続|*|*|*|*|*
名詞|固有名詞|組織|*|*|*|*
記号|空白|*|*|*|*|*
名詞|固有名詞|組織|*|*|*|*
助詞|格助詞|連語|*|*|*|って|ッテ|ッテ
助詞|接続助詞|*|*|*|*|ば|バ|バ
副詞|助詞類接続|*|*|*|*|たいてい|タイテイ|タイテイ
助詞|係助詞|*|*|*|*|は|ハ|ワ
形容詞|自立|*|*|形容詞・イ段|基本形|優しい|ヤサシイ|ヤサシイ

  正規表現は、、、
  val targetString = "@hogebot westってばemacsユーザには優しいらしい"
  val regex = """[\w|@]+""".r
  regex.findAllIn(targetString).mkString("|")

  regex.findAllIn(targetString) map {
    case "@hogebot" => "this is me!"
    case w:String => w
  } foreach { t =>
    println(t)
  }

  更に自分はフィルタしてってこうするとcaseが要らんね。。
  まあ英単語は全部アカウント名とみなしてもいいか。。。
  
  regex.findAllIn(targetString) filter {
    _ != "@hogebot"
  } map {
//    case "@hogebot" => "this is me!"
    case w:String => w
  } foreach { t =>
    println(t)
  }

  
  emacsって名詞かな？無理か。。。
  -> 一応一般名詞らしい。。。
  val tokens = tokenizer.tokenize("@hogebot westってばemacsユーザには優しいらしい");
  
名詞|サ変接続|*|*|*|*|*
名詞|固有名詞|組織|*|*|*|*
記号|空白|*|*|*|*|*
名詞|固有名詞|組織|*|*|*|*
助詞|格助詞|連語|*|*|*|って|ッテ|ッテ
助詞|接続助詞|*|*|*|*|ば|バ|バ
名詞|一般|*|*|*|*|*
名詞|一般|*|*|*|*|ユーザ|ユーザ|ユーザ
助詞|格助詞|一般|*|*|*|に|ニ|ニ
助詞|係助詞|*|*|*|*|は|ハ|ワ
形容詞|自立|*|*|形容詞・イ段|基本形|優しい|ヤサシイ|ヤサシイ
助動詞|*|*|*|形容詞・イ段|基本形|らしい|ラシイ|ラシイ
  
}

  val m1 = InputMessage("ねえ @hogebot k_nishijimaさんについて教えて？")
  val m1 = InputMessage("ねえ @hogebot kimihitoさんはemacs使えるの？")

  for (token <- m1.tokens) println(token.getSurfaceForm +"|"+ token.getPosition  +"|"+ token.getAllFeaturesArray()(0))

  
  **/

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
//    this.tokens.exists(_.getAllFeaturesArray().length > 8 && _.getAllFeaturesArray()(7) == "オシエ") &&
//      (msg.last == "?" || msg.last == "？")

    true
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



class MsgParser {
  def getTarget(msg: String) {

  }

  def getRating(mgs: String) {
    val tokenizer = Tokenizer.builder().build()
    val tokens = tokenizer.tokenize("ねえ @hogebot k_nishijimaさんについて教えて？");

    for (token <- tokens) println(token.getAllFeaturesArray.mkString("|"))
  }
}
