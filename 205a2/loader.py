import curses
import pickle
import string  # Serialization module for python


class Document:
    """Pages are presumed to have dimensions of 80 wide by 20 tall."""

    def __init__(self, filename=None):
        self.filename = filename if filename else "binary.bindoc"
        self.pages = []
        self.current = 0
        self.addPage()

    def addPage(self):
        self.pages.append(["".ljust(80) for i in range(20)])
        self.nextPage()

    def delPage(self):
        self.pages.pop(self.current)
        if len(self.pages) <= 0:
            self.addPage()
        else:
            self.prevPage()

    def setChr(self, r, c, ltr):
        self.pages[self.current][r] = self.pages[self.current][r][:c] + ltr + self.pages[self.current][r][c + 1:]

    def nextPage(self):
        self.current = (self.current + 1) % len(self.pages)

    def prevPage(self):
        self.current = (self.current - 1 + len(self.pages)) % len(self.pages)

    def __str__(self):
        return "\n".join(self.pages[self.current])


d = Document()
class Tree:
    def _init_(self,filename,parent=None,left=None,middle=None,right=None):
        self.filename=filename
        self.parent=parent
        self.left=left
        self.middle=middle
        self.right=right
t= Tree()
def drawscreen(scr, doc):
    height, width = scr.getmaxyx()
    if height < 24 or width < 80:
        scr.move(0, 0)
        scr.erase()
        curses.doupdate()
        return
    pos_r, pos_c = curses.getsyx()
    scr.hline(20, 0, '~', width)
    pos_p = str(doc.current + 1) + '/' + str(len(doc.pages))  # Not displaying zero-based indexing
    scr.addstr(20, width - len(pos_p), pos_p)

    commands = [["^C: Quit", "^O: Story save", "^L: Story load"],
                ["^X: Back to write","^V: Set Node"]]

    for r in range(2):
        ct = 0
        for cmd in commands[r]:
            scr.addstr(21 + r, ct * 20 + 5, cmd, curses.A_REVERSE)
            ct += 1
    if width > 80:  # if we need to fill in the excess space to the right of the document...
        for row in range(height - 4):
            scr.addstr(row, 80, " " * (width - 80), curses.A_REVERSE)
    scr.move(0, 0)
    lines = str(doc).split('\n')
    for line in range(len(lines)):
        scr.addstr(line, 0, lines[line])
    scr.move(pos_r, pos_c)


def sizecheck(scr):
    h, w = scr.getmaxyx()
    return h, w, h >= 24 and w >= 80


def main(stdscr):
    global d
    stdscr.clear()
    drawscreen(stdscr, d)
    stdscr.move(0, 0)
    s_height, s_width, enabled = sizecheck(stdscr)
    flag = 0
    stdscr.addstr("File build: ")
    while True:
        if (flag == 0):
            stdscr.addstr(" enter file number:")
            flag = 1
            e = stdscr.getch() - 48
            e1 = ".bindoc"
            e2 = str(e) + e1
            stdscr.addstr(str(e))
            d.filename = e2
            stdscr.addstr(" file")
            stdscr.move(0+ 1, 0)
        c = stdscr.getch()
        if enabled:
            if c == curses.KEY_UP:
                pos_r, pos_c = curses.getsyx()
                pos_r = max(pos_r - 1, 0)
                stdscr.move(pos_r, pos_c)
            elif c == curses.KEY_DOWN:
                pos_r, pos_c = curses.getsyx()
                pos_r = min(pos_r + 1, 19)
                stdscr.move(pos_r, pos_c)
            elif c == curses.KEY_LEFT:
                pos_r, pos_c = curses.getsyx()
                pos_c = max(pos_c - 1, 0)
                stdscr.move(pos_r, pos_c)
            elif c == curses.KEY_RIGHT:
                pos_r, pos_c = curses.getsyx()
                pos_c = min(pos_c + 1, 79)
                stdscr.move(pos_r, pos_c)
            elif c >= 32 and c <= 126:  # Matches on any of teh 'standard' printable characters.
                pos_r, pos_c = curses.getsyx()
                stdscr.addstr(pos_r, pos_c, chr(c))
                d.setChr(pos_r, pos_c, chr(c))
                if pos_r >= 19 and pos_c >= 79:
                    stdscr.move(pos_r, pos_c)
            elif c == curses.KEY_HOME:  # Jump to start of the current line
                pos_c = 0
                stdscr.move(pos_r, pos_c)
            elif c == curses.KEY_END:  # Jump to end of the current line
                pos_c = 79
                stdscr.move(pos_r, pos_c)
            elif c == curses.KEY_PPAGE:  # PGUP (previous page)
                d.prevPage()
                drawscreen(stdscr, d)
                pos_r, pos_c = 0, 0
                stdscr.move(pos_r, pos_c)
            elif c == curses.KEY_NPAGE:  # PGDN (next page)
                d.nextPage()
                drawscreen(stdscr, d)
                pos_r, pos_c = 0, 0
                stdscr.move(pos_r, pos_c)
            elif c == curses.KEY_IC:  # Insert (add page)
                d.addPage()
                drawscreen(stdscr, d)
                pos_r, pos_c = 0, 0
                stdscr.move(pos_r, pos_c)
            elif c == curses.KEY_DC:  # Delete (remove page)
                d.delPage()
                drawscreen(stdscr, d)
                pos_r, pos_c = 0, 0
                stdscr.move(pos_r, pos_c)
            elif c == curses.KEY_BACKSPACE or curses.keyname(c) == '^H':  # ^H is weirdness for some terminals
                pos_r, pos_c = curses.getsyx()
                pos_c -= 1
                if pos_c < 0:
                    pos_c = s_width - 1
                    pos_r -= 1
                    if pos_r < 0:
                        pos_r = 0
                        pos_c = 0
                stdscr.addch(pos_r, pos_c, 32)
                d.setChr(pos_r, pos_c, ' ')
                stdscr.move(pos_r, pos_c)
            elif curses.keyname(c) == '^X':
                d.delPage()
                drawscreen(stdscr, d)
                stdscr.move(0,0)
                stdscr.addstr("File build: ")
                flag=0;
            elif curses.keyname(c) == '^V':
                #http://www.openbookproject.net/thinkcs/python/english2e/ch21.html
                #https://stackoverflow.com/questions/2358045/how-can-i-implement-a-tree-in-python-are-there-any-built-in-data-structures-in
                pass
            elif c == 10:  # linefeed
                pos_r, pos_c = curses.getsyx()
                pos_c = 0
                pos_r = min(pos_r + 1, 19)
                stdscr.move(pos_r, pos_c)
            elif curses.keyname(c) == '^L':  # ctrl+l - Binary restore
                try:
                    f = open(d.filename, 'r')  # open in 'read' mode
                    d = pickle.load(f)
                    f.close()
                except IOError:  # e.g. if the file doesn't exist yet
                    pass
                drawscreen(stdscr, d)
            elif curses.keyname(c) == '^O':  # ctrl+o - Binary save
                f = open(d.filename, 'w')  # open in 'write' mode
                pickle.dump(d, f)  # pickling is much simpler than Java serialization
                #if(t.left==None):
                #   t.filename=str(d.filename)
                #elif(t.middle==None):
                #   t.filename=str(d.filename)
                #elif(t.right==None):
                #   t.filename=str(d.filename)
                #else
                #   t.left=Tree()
                f.close()
            elif c == curses.KEY_RESIZE:  # As odd as it sounds, resizing the terminal is treated like any other character
                s_height, s_width, enabled = sizecheck(stdscr)
                drawscreen(stdscr, d)
        # else: #eventually delete this
        #	stdscr.addstr(0,0,curses.keyname(c))
        else:  # We want to keep everything disabled until the window is back to a legal size
            if c == curses.KEY_RESIZE:  # We still need to listen for this one, so we'll know it's safe again
                s_height, s_width, enabled = sizecheck(stdscr)
                drawscreen(stdscr, d)
        curses.doupdate()


# The try/except is just so pressing ctrl+c is treated as 'normal'
# If we'd wanted to throw up a, "you have unsaved work!" warning, we could've put it inside the loop instead
try:
    curses.wrapper(
        main)  # We use this wrapper to ensure the terminal is restored to a 'normal' state, even if something crashes
    pass
except KeyboardInterrupt:
    pass
