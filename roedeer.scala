/***
  libraryDependencies ++= Seq(
  "com.github.scala-incubator.io" % "scala-io-core_2.10" % "0.4.2",
  "com.github.scala-incubator.io" % "scala-io-file_2.10" % "0.4.2"
  ) 
 */

import com.hoppinglife.roedeer._
import scalax.io._

object roeDeer {

  def detect_vundle() =  {
    if(env.vundle_path.exists)
      true
    else
      false
  }

  def main(args: Array[String]) {
    println("This is roedeer vim configuration pack, v0.1")
    println("Backing your current vimrc file")
    env.backup_vimrc()
    println("Updating vimrc file...")

    val output = Resource.fromFile(env.vimrc_path.path)
    output.write(vundle.vimrc_setting())
    output.write(beforeSettingGroup.getConfiguration)
    output.write(":source " + env.vimrc_after_path.path + "\n")

    println("updating vundle settings...")
    vundle.update_vundle()
  }
}