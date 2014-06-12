package com.hoppinglife.roedeer

/**
 * Created by snmyj on 12/25/13.
 */
object beforeSettingGroup extends ConfigurationGenerator {
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

  def display() : String = {
    """ |
      |set background=dark
      |colorscheme solarized
      |
      |set number
      |set ruler
      |syntax enable
      |set encoding=utf-8
      |""".stripMargin
  }

  def search() : String = {
    """ |set hlsearch
      |set incsearch
      |set ignorecase
      |set smartcase
      |""".stripMargin
  }

  def wilds() : String = {
    """ |
      |set wildignore+=*.o,*.out,*.obj,.git,*.rbc,*.rbo,*.class,.svn,*.gem
      |set wildignore+=*.zip,*.tar.gz,*.tar.bz2,*.rar,*.tar.xz
      |set wildignore+=*/vendor/gems/*,*/vendor/cache/*,*/.bundle/*,*/.sass-cache/*
      |""".stripMargin
  }

  def gui() : String = {
    """ |
      |set guioptions-=m
      |set guioptions-=T
      |set guioptions-=r
      |""".stripMargin
  }

  override val functions : List[() => String] = List[()=>String](
    whitespace,
    display,
    search,
    wilds,
    gui
  )
}
