set CDIR=%CD%
cd %USERPROFILE%
git clone https://github.com/gmarik/Vundle.vim.git vimfiles/bundle/vundle
cd %CDIR%
sbt run
