package com.hoppinglife.roedeer

/**
 * Created by snmyj on 12/25/13.
 */
trait ConfigurationGenerator {
  val functions : List[()=>String];
  def getConfiguration() = {
    val conf_group = for(f <- functions)
       yield f()
    conf_group.mkString
  }
}