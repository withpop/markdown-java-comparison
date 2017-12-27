import io.github.gitbucket.markedj.Marked

import scala.io.Source

object Main extends App {
  val samples = List(
    Source.fromResource("sample1.md").getLines().mkString("\n"),
    Source.fromResource("sample2.md").getLines().mkString("\n"),
    Source.fromResource("sample3.md").getLines().mkString("\n"),
    Source.fromResource("sample4.md").getLines().mkString("\n"),
  )

  BenchMark.markedj(samples)
  BenchMark.flexmark(samples)
  BenchMark.commonmark(samples)
}

object BenchMark {

  import org.scalameter._

  def markedj(mds: List[String]) = {
    // warm up
    mds.foreach(Marked.marked)

    println("markedj -----------")
    mds.zipWithIndex.foreach {
      case (md, i) =>
        println(s"sample" + (i + 1) + " " + measure {
          Marked.marked(md)
        })
    }
  }

  def commonmark(mds: List[String]) = {
    import org.commonmark.parser.Parser
    import org.commonmark.renderer.html.HtmlRenderer

    val parser = Parser.builder.build
    val renderer = HtmlRenderer.builder.build

    // warm up
    mds.foreach { md =>
      renderer.render(parser.parse(md))
    }

    println("commonmark -----------")
    mds.zipWithIndex.foreach {
      case (md, i) =>
        println(s"sample" + (i + 1) + " " + measure {
          renderer.render(parser.parse(md))
        })
    }
  }

  def flexmark(mds: List[String]) = {
    import com.vladsch.flexmark.html.HtmlRenderer
    import com.vladsch.flexmark.parser.Parser
    import com.vladsch.flexmark.util.options.MutableDataSet

    val options = new MutableDataSet
    val parser = Parser.builder(options).build
    val renderer = HtmlRenderer.builder(options).build

    // warm up
    mds.foreach { md =>
      renderer.render(parser.parse(md))
    }

    println("flexmark -----------")
    mds.zipWithIndex.foreach {
      case (md, i) =>
        println(s"sample" + (i + 1) + " " + measure {
          Marked.marked(md)
        })
    }
  }
}