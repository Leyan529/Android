#!/usr/bin/env python
from HTMLParser import HTMLParser
import sys

if len(sys.argv) != 2:
    print "Error of parameters"
    sys.exit(1)
FILEPATH=sys.argv[1]

# create a subclass and override the handler methods
class MyHTMLParser(HTMLParser):
    def __init__(self):
        HTMLParser.__init__(self)
        self.res_section_header = False
        self.res_section_data = False
        self.target_flag = False
        self.counter = 0

        self.results = {}
        self.key = None 

    def handle_starttag(self, tag, attrs):
        if not self.res_section_data:
            if tag == "h2":
                self.res_section_header = True
        else:
            if tag == "td":
                self.target_flag = True
            else:
                self.target_flag = False

    def handle_endtag(self, tag):
        pass

    def handle_data(self, data):
        if data == "\n":
            return
        if self.target_flag:
            if self.key:
                self.results[self.key] = False if "failed" in data else True
                self.key = None
            else:
                self.key = data
        elif self.res_section_header:
            if data == "Tests":
                self.res_section_data = True
            else:
                self.res_section_header = False

parser = MyHTMLParser()

def show_result(res):
    for k, v in res.iteritems():
        print "{}  {} ".format(k, "Success" if v else "Failed")

with open(FILEPATH, 'r') as infile:
    data = str(infile.read())
    parser.feed(data)
    show_result(parser.results)