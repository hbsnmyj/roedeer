package com.hoppinglife.roedeer {

import scalax.io._
import scalax.file.Path
import java.util.Date

object env {
  val os_str = System.getProperty("os.name").toLowerCase();
  val vimrc_name = if(env.is_windows()) "_vimrc" else ".vimrc"
  val _vim_path = if(env.is_windows()) "vimfiles" else ".vim"
  val home_dir = System.getProperty("user.home")
  val vimrc_path = Path.fromString(home_dir) / vimrc_name
  val vimrc_after_path = Path.fromString(vimrc_path.path + "_after")
  val vundle_path = Path.fromString(home_dir) / _vim_path / "bundle" / "vundle"
  val ycm_path = Path.fromString(home_dir) / _vim_path / "ycm"

  def is_windows() = {
    os_str.indexOf("win") >= 0;
  }
  def backup_vimrc() = {
    if(vimrc_path.exists) {
      vimrc_path.copyTo(
        Path.fromString(vimrc_path.path + ".roe_deer_bak" + (new Date().hashCode).toString))
      vimrc_path.delete()
    }
  }


}

}