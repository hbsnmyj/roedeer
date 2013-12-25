/***
	libraryDependencies ++= Seq(
		"com.github.scala-incubator.io" % "scala-io-core_2.10" % "0.4.2",
		"com.github.scala-incubator.io" % "scala-io-file_2.10" % "0.4.2"
	) 
*/

import scalax.io._
import scalax.file.Path
import scala.sys.process._
import java.util.Date
import scala.collection.mutable.ListBuffer

var first_setting_group = new ListBuffer[() => String]()
def vundle() : String = {
	""" |set nocompatible
	|filetype off
	|
	|set rtp+=~/vimfiles/bundle/vundle
	|call vundle#rc()
	|
	|Bundle 'gmarik/vundle'
	|Bundle 'tpope/vim-fugitive'
	|Bundle 'scrooloose/nerdtree'
	|Bundle 'scrooloose/nerdcommenter'
	|
	|filetype plugin indent on
	""".stripMargin
}
first_setting_group += vundle _

def display() : String = {
	""" |
		|set background=dark
		|colorscheme desert
		|
		|set number
		|set ruler
		|syntax enable
		|set encoding=utf-8
		|""".stripMargin
}
first_setting_group += display _;

def whitespace() : String = {
	""" |
		|set nowrap
		|set expandtab
		|set tabstop=2
		|set shiftwidth=2
		|
		|set list
		|set backspace=indent,eol,start
		|
		|set listchars=""
		|set listchars=tab:\ \ 
		|set listchars+=trail:.
		|set listchars+=extends:>
		|set listchars+=precedes:<
		|""".stripMargin
}
first_setting_group += whitespace _;

def search() : String = {
	""" |set hlsearch
		|set incsearch
		|set ignorecase
		|set smartcase
		|""".stripMargin
}
first_setting_group += search _

def wilds() : String = {
	""" |
		|set wildignore+=*.o,*.out,*.obj,.git,*.rbc,*.rbo,*.class,.svn,*.gem
		|set wildignore+=*.zip,*.tar.gz,*.tar.bz2,*.rar,*.tar.xz
		|set wildignore+=*/vendor/gems/*,*/vendor/cache/*,*/.bundle/*,*/.sass-cache/*
		|""".stripMargin
}
first_setting_group += wilds _

def gui() : String = {
	""" |
		|set guioptions-=m
		|set guioptions-=T
		|set guioptions-=r
		|""".stripMargin
}
first_setting_group += gui _

val home_dir = System.getProperty("user.home")
val vimrc_path = Path.fromString(home_dir) / "_vimrc"

if(vimrc_path.exists) {
	vimrc_path.copyTo(
		Path.fromString(vimrc_path.path + ".roe_deer_bak" + (new Date().hashCode).toString))
	vimrc_path.delete()
}
	

val output = Resource.fromFile(vimrc_path.path)
val func_list = first_setting_group.toList
for( func <- func_list ) {
	output.write(func())
}

println("Updateing vundle ...\n")
"""vim +BundleInstall +qall""".!
