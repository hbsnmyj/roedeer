package com.hoppinglife.roedeer

import scala.collection.GenIterable
import scala.sys.process.Process

/**
 * Created by snmyj on 12/25/13.
 */
object vundle extends ConfigurationGenerator {
  def default_plugins = List(
    "gmarik/vundle",
    "tpope/vim-fugitive",
    "tpope/vim-unimpaired",
    "scrooloose/nerdtree",
    "scrooloose/nerdcommenter",
    "scrooloose/syntastic",
    "Rip-Rip/clang_complete",
    "ervandew/supertab",
    "vim-scripts/EasyMotion",
    "vim-scripts/ZoomWin",
    "chrisbra/NrrwRgn",
    "jeetsukumaran/vim-buffergator",
    "skalnik/vim-vroom",
    "majutsushi/tagbar"
  )

  def ycm_code() : String = {
    if(env.is_windows())
      "\n" + """Bundle 'file:///""" + env.ycm_path.path + "\'\n"
    else
      ""
  }

  def bundle_code() : String = {
    (for ( x <- default_plugins) yield
      "Bundle \'"+ x +"\'")
      .mkString("\n")
  }

  def vimrc_setting() : String = {
    """ |
      |set nocompatible
      |filetype off
      |
      |set rtp+=~/vimfiles/bundle/vundle
      |call vundle#rc()
      |
      |""".stripMargin +
      bundle_code() +
      """|
        |filetype plugin indent on
        |""".stripMargin
  }

  def update_vundle() {
    println(Process("gvim +BundleInstall +qall").!!)
  }

  override val functions = List[ () => String] (
    vimrc_setting
  )
}
